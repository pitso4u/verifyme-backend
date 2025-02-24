const express = require('express');
const router = express.Router();
const incidentController = require('../controllers/incidentController');

// Incident routes
router.post('/', incidentController.createIncident); // Create a new incident record
router.get('/', incidentController.getAllIncidents); // Get all incidents
router.get('/:id', incidentController.getIncidentById); // Get incident by ID
router.put('/:id', incidentController.updateIncident); // Update incident
router.delete('/:id', incidentController.deleteIncident); // Delete incident

module.exports = router; 