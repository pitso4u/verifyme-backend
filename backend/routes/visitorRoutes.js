const express = require('express');
const router = express.Router();
const visitorController = require('../controllers/visitorController');
const Visitor = require('../models/Visitor'); // Ensure you have a Visitor model

// Visitor routes
router.post('/', async (req, res) => {
    const { name, purpose, entryTime, department, remarks } = req.body;
    try {
        const newVisitor = await Visitor.create({
            name,
            purpose,
            entryTime,
            department,
            remarks
        });
        res.status(201).json(newVisitor);
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: error.message });
    }
});

router.get('/', async (req, res) => {
    try {
        const visitors = await Visitor.findAll(); // Adjust this based on your ORM
        res.json(visitors);
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: error.message });
    }
});

router.get('/:id', visitorController.getVisitorById); // Get visitor by ID
router.put('/:id', visitorController.updateVisitor); // Update visitor
router.delete('/:id', visitorController.deleteVisitor); // Delete visitor

module.exports = router; 