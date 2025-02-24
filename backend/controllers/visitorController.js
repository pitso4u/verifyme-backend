const Visitor = require('../models/Visitor');

// Create a new visitor record
exports.createVisitor = async (req, res) => {
    try {
        const visitor = await Visitor.create(req.body);
        res.status(201).json(visitor);
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
};

// Get all visitors
exports.getAllVisitors = async (req, res) => {
    try {
        const visitors = await Visitor.findAll();
        res.status(200).json(visitors);
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
};

// Get visitor by ID
exports.getVisitorById = async (req, res) => {
    try {
        const visitor = await Visitor.findByPk(req.params.id);
        if (visitor) {
            res.status(200).json(visitor);
        } else {
            res.status(404).json({ message: 'Visitor not found' });
        }
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
};

// Update visitor
exports.updateVisitor = async (req, res) => {
    try {
        const [updated] = await Visitor.update(req.body, {
            where: { id: req.params.id }
        });
        if (updated) {
            const updatedVisitor = await Visitor.findByPk(req.params.id);
            res.status(200).json(updatedVisitor);
        } else {
            res.status(404).json({ message: 'Visitor not found' });
        }
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
};

// Delete visitor
exports.deleteVisitor = async (req, res) => {
    try {
        const deleted = await Visitor.destroy({
            where: { id: req.params.id }
        });
        if (deleted) {
            res.status(204).send();
        } else {
            res.status(404).json({ message: 'Visitor not found' });
        }
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
}; 