const { Sequelize, DataTypes } = require('sequelize');
const sequelize = require('../config/database');
const User = require('./User');

const Incident = sequelize.define('Incident', {
    id: {
        type: DataTypes.INTEGER,
        autoIncrement: true,
        primaryKey: true,
    },
    incidentType: {
        type: DataTypes.STRING,
        allowNull: false,
    },
    description: {
        type: DataTypes.TEXT,
        allowNull: false,
    },
    occurredAt: {
        type: DataTypes.DATE,
        allowNull: false,
    },
    reportedAt: {
        type: DataTypes.DATE,
        defaultValue: DataTypes.NOW,
    },
    location: {
        type: DataTypes.STRING,
        allowNull: false,
    },
    reportedBy: {
        type: DataTypes.INTEGER,
        references: {
            model: User,
            key: 'id',
        },
    },
    involvedParties: {
        type: DataTypes.STRING,
        allowNull: true,
    },
    status: {
        type: DataTypes.STRING,
        allowNull: false,
    },
    resolution: {
        type: DataTypes.TEXT,
        allowNull: true,
    },
    followUpNeeded: {
        type: DataTypes.BOOLEAN,
        defaultValue: false,
    },
    notes: {
        type: DataTypes.TEXT,
        allowNull: true,
    },
    createdBy: {
        type: DataTypes.INTEGER,
        references: {
            model: User,
            key: 'id',
        },
    },
    createdDate: {
        type: DataTypes.DATE,
        defaultValue: DataTypes.NOW,
    },
}, {
    timestamps: true,
});

module.exports = Incident; 