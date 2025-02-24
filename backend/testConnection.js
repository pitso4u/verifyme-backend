const { Sequelize } = require('sequelize');

const sequelize = new Sequelize('verifyme', 'postgres', 'Soetsang@144156', {
    host: 'localhost',
    port: 5433,
    dialect: 'postgres',
});

async function testConnection() {
    try {
        await sequelize.authenticate();
        console.log('Connection has been established successfully.');
    } catch (error) {
        console.error('Unable to connect to the database:', error);
    }
}

testConnection(); 