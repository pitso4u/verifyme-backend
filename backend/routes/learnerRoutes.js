const express = require('express');
const router = express.Router();
const learnerController = require('../controllers/learnerController');

// Learner routes
router.post('/', learnerController.createLearner); // Create a new learner record
router.get('/', learnerController.getAllLearners); // Get all learners
router.get('/:id', learnerController.getLearnerById); // Get learner by ID
router.put('/:id', learnerController.updateLearner); // Update learner
router.delete('/:id', learnerController.deleteLearner); // Delete learner

module.exports = router; 