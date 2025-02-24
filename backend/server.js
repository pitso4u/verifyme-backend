const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const attendanceRoutes = require('./routes/attendanceRoutes');
const learnerRoutes = require('./routes/learnerRoutes');
const employeeRoutes = require('./routes/employeeRoutes');
const visitorRoutes = require('./routes/visitorRoutes');
const Attendance = require('./models/Attendance');
const User = require('./models/User'); // Import the User model
const Learner = require('./models/Learner'); // Import the Learner model
const Employee = require('./models/Employee'); // Import the Employee model

const app = express();

// Middleware
app.use(bodyParser.json());
app.use(cors());

// Routes
app.use('/api/attendance', attendanceRoutes);
app.use('/api/learners', learnerRoutes);
app.use('/api/employees', employeeRoutes);
app.use('/api/visitors', visitorRoutes);

// QR code and Face Recognition endpoint for mobile app
app.post('/api/scan-qr', async (req, res) => {
    try {
        const { learnerId, timestamp, method } = req.body;
        let userId = learnerId;
        let attendanceMethod = 'QR code';

        if (method === 'face recognition') {
            // Simulate face recognition - for now, assume a hardcoded employee ID
            userId = 'employee123'; // Replace with actual employee ID from face recognition
            attendanceMethod = 'face recognition';
        }

        const newAttendance = await Attendance.create({
            userId: userId,
            timestamp: timestamp ? new Date(timestamp) : new Date(),
            method: attendanceMethod,
        });
        res.status(201).json({ message: 'Attendance recorded successfully', attendance: newAttendance });
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: error.message });
    }
});
app.post('/api/login', async (req, res) => {
    const { username, password } = req.body;
    try {
        const user = await User.findOne({ where: { username, password } });
        if (user) {
            res.json({ token: "Successful login" });
        } else {
            res.status(401).json({ message: "Invalid credentials" });
        }
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: error.message });
    }
});

// Endpoint to log attendance from QR code data
app.post('/api/logAttendance', async (req, res) => {
    const { qrCodeData } = req.body;
    try {
        // Process the qrCodeData to determine if it's a learner or an employee
        let user;
        if (qrCodeData.startsWith('L')) { // Assuming learner IDs start with 'L'
            user = await Learner.findOne({ where: { studentId: qrCodeData } });
            if (!user) {
                return res.status(404).json({ message: 'Learner not found.' });
            }
            // Log attendance for learner
            await Attendance.create({
                userId: user.id,
                role: 'learner',
                status: 'present',
                timestamp: new Date(),
            });
        } else if (qrCodeData.startsWith('E')) { // Assuming employee IDs start with 'E'
            user = await Employee.findOne({ where: { employeeId: qrCodeData } });
            if (!user) {
                return res.status(404).json({ message: 'Employee not found.' });
            }
            // Log attendance for employee
            await Attendance.create({
                userId: user.id,
                role: 'staff',
                status: 'present',
                timestamp: new Date(),
            });
        } else {
            return res.status(400).json({ message: 'Invalid QR code data.' });
        }

        // Respond with success
        res.status(200).json({ message: 'Attendance logged successfully.' });
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: error.message });
    }
});

const PORT = process.env.PORT || 5001; // Changed port to 5001
app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});