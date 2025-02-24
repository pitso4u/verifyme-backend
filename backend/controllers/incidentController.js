const Incident = require('../models/Incident');

// Create a new incident record
exports.createIncident = async (req, res) => {
    try {
        const incident = await Incident.create(req.body);
        res.status(201).json(incident);
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
};

// Get all incidents
exports.getAllIncidents = async (req, res) => {
    try {
        const incidents = await Incident.findAll();
        res.status(200).json(incidents);
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
};

// Get incident by ID
exports.getIncidentById = async (req, res) => {
    try {
        const incident = await Incident.findByPk(req.params.id);
        if (incident) {
            res.status(200).json(incident);
        } else {
            res.status(404).json({ message: 'Incident not found' });
        }
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
};

// Update incident
exports.updateIncident = async (req, res) => {
    try {
        const [updated] = await Incident.update(req.body, {
            where: { id: req.params.id }
        });
        if (updated) {
            const updatedIncident = await Incident.findByPk(req.params.id);
            res.status(200).json(updatedIncident);
        } else {
            res.status(404).json({ message: 'Incident not found' });
        }
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
};

// Delete incident
exports.deleteIncident = async (req, res) => {
    try {
        const deleted = await Incident.destroy({
            where: { id: req.params.id }
        });
        if (deleted) {
            res.status(204).send();
        } else {
            res.status(404).json({ message: 'Incident not found' });
        }
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
}; 