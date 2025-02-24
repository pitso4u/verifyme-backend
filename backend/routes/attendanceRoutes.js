const express = require('express');
const router = express.Router();
const attendanceController = require('../controllers/attendanceController');

// Create a new attendance record
router.post('/', attendanceController.createAttendance);

// Get all attendance records
router.get('/', attendanceController.getAllAttendance);

// Get attendance record by ID
router.get('/:id', attendanceController.getAttendanceById);

// Update attendance record
router.put('/:id', attendanceController.updateAttendance);

// Delete attendance record
router.delete('/:id', attendanceController.deleteAttendance);

module.exports = router;