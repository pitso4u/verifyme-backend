const express = require('express');
const Employee = require('../models/Employee'); // Import the Employee model
const router = express.Router();
const employeeController = require('../controllers/employeeController');

// Employee routes
router.post('/', async (req, res) => {
    try {
        const { userId, department } = req.body; // Ensure these fields are being sent
        const newEmployee = await Employee.create({ userId, department });
        res.status(201).json(newEmployee);
    } catch (error) {
        console.error(error);
        res.status(400).json({ error: 'Failed to create employee' }); // Return a 400 error if something goes wrong
    }
});

router.get('/', async (req, res) => {
    try {
        const employees = await Employee.findAll();
        res.status(200).json(employees);
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: 'Failed to retrieve employees' }); // Return a 500 error if something goes wrong
    }
});

router.get('/:id', employeeController.getEmployeeById); // Get employee by ID
router.put('/:id', employeeController.updateEmployee); // Update employee
router.delete('/:id', employeeController.deleteEmployee); // Delete employee

module.exports = router; 