const Attendance = require('../models/Attendance');

// Create a new attendance record
exports.createAttendance = async (req, res) => {
    try {
        const attendance = await Attendance.create(req.body);
        res.status(201).json(attendance);
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
};

// Get all attendance records
exports.getAllAttendance = async (req, res) => {
    try {
        const attendanceRecords = await Attendance.findAll();
        res.status(200).json(attendanceRecords);
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
};

// Get attendance by ID
exports.getAttendanceById = async (req, res) => {
    try {
        const attendance = await Attendance.findByPk(req.params.id);
        if (attendance) {
            res.status(200).json(attendance);
        } else {
            res.status(404).json({ message: 'Attendance record not found' });
        }
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
};

// Update attendance
exports.updateAttendance = async (req, res) => {
    try {
        const [updated] = await Attendance.update(req.body, {
            where: { id: req.params.id }
        });
        if (updated) {
            const updatedAttendance = await Attendance.findByPk(req.params.id);
            res.status(200).json(updatedAttendance);
        } else {
            res.status(404).json({ message: 'Attendance record not found' });
        }
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
};

// Delete attendance
exports.deleteAttendance = async (req, res) => {
    try {
        const deleted = await Attendance.destroy({
            where: { id: req.params.id }
        });
        if (deleted) {
            res.status(204).send();
        } else {
            res.status(404).json({ message: 'Attendance record not found' });
        }
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
};