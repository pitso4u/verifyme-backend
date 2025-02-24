const Learner = require('../models/Learner');

// Create a new learner record
exports.createLearner = async (req, res) => {
    try {
        const learner = await Learner.create(req.body);
        res.status(201).json(learner);
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
};

// Get all learners
exports.getAllLearners = async (req, res) => {
    try {
        const learners = await Learner.findAll();
        res.status(200).json(learners);
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
};

// Get learner by ID
exports.getLearnerById = async (req, res) => {
    try {
        const learner = await Learner.findByPk(req.params.id);
        if (learner) {
            res.status(200).json(learner);
        } else {
            res.status(404).json({ message: 'Learner not found' });
        }
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
};

// Update learner
exports.updateLearner = async (req, res) => {
    try {
        const [updated] = await Learner.update(req.body, {
            where: { id: req.params.id }
        });
        if (updated) {
            const updatedLearner = await Learner.findByPk(req.params.id);
            res.status(200).json(updatedLearner);
        } else {
            res.status(404).json({ message: 'Learner not found' });
        }
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
};

// Delete learner
exports.deleteLearner = async (req, res) => {
    try {
        const deleted = await Learner.destroy({
            where: { id: req.params.id }
        });
        if (deleted) {
            res.status(204).send();
        } else {
            res.status(404).json({ message: 'Learner not found' });
        }
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
}; 