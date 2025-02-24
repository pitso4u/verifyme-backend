const request = require('supertest');
const app = require('../server'); // Import the app instance
const sequelize = require('../config/database');

beforeAll(async () => {
    await sequelize.sync({ force: true }); // Use force: true to drop existing tables
});

afterAll(async () => {
    await sequelize.close(); // Close the database connection after tests
    // If you have a server instance, you can close it here if needed
    // await app.close(); // Uncomment if you have a close method
});

describe('API Endpoints', () => {
    // Test User Endpoints
    describe('User Endpoints', () => {
        it('should create a new user', async () => {
            const response = await request(app)
                .post('/api/users')
                .send({
                    username: 'testuser',
                    password: 'testpassword',
                    email: 'testuser@example.com',
                    role: 'learner',
                });
            expect(response.statusCode).toBe(201);
            expect(response.body).toHaveProperty('id');
        });

        it('should get all users', async () => {
            const response = await request(app).get('/api/users');
            expect(response.statusCode).toBe(200);
            expect(Array.isArray(response.body)).toBe(true);
        });
    });

    // Test Attendance Endpoints
    describe('Attendance Endpoints', () => {
        it('should create a new attendance record', async () => {
            const response = await request(app)
                .post('/api/attendance')
                .send({
                    userId: 1, // Adjust based on your test data
                    method: 'face recognition',
                    trainingPhoto: 'path/to/photo.jpg',
                });
            expect(response.statusCode).toBe(201);
            expect(response.body).toHaveProperty('id');
        });

        it('should get all attendance records', async () => {
            const response = await request(app).get('/api/attendance');
            expect(response.statusCode).toBe(200);
            expect(Array.isArray(response.body)).toBe(true);
        });
    });

    // Test Visitor Endpoints
    describe('Visitor Endpoints', () => {
        it('should create a new visitor record', async () => {
            const response = await request(app)
                .post('/api/visitors')
                .send({
                    name: 'John Doe',
                    purpose: 'Meeting',
                });
            expect(response.statusCode).toBe(201);
            expect(response.body).toHaveProperty('id');
        });

        it('should get all visitors', async () => {
            const response = await request(app).get('/api/visitors');
            expect(response.statusCode).toBe(200);
            expect(Array.isArray(response.body)).toBe(true);
        });
    });

    // Test Incident Endpoints
    describe('Incident Endpoints', () => {
        it('should create a new incident record', async () => {
            const response = await request(app)
                .post('/api/incidents')
                .send({
                    userId: 1, // Adjust based on your test data
                    description: 'Incident description',
                });
            expect(response.statusCode).toBe(201);
            expect(response.body).toHaveProperty('id');
        });

        it('should get all incidents', async () => {
            const response = await request(app).get('/api/incidents');
            expect(response.statusCode).toBe(200);
            expect(Array.isArray(response.body)).toBe(true);
        });
    });

    // Test Learner Endpoints
    describe('Learner Endpoints', () => {
        it('should create a new learner record', async () => {
            const response = await request(app)
                .post('/api/learners')
                .send({
                    userId: 1, // Adjust based on your test data
                    course: 'Mathematics',
                });
            expect(response.statusCode).toBe(201);
            expect(response.body).toHaveProperty('id');
        });

        it('should get all learners', async () => {
            const response = await request(app).get('/api/learners');
            expect(response.statusCode).toBe(200);
            expect(Array.isArray(response.body)).toBe(true);
        });
    });

    // Test Employee Endpoints
    describe('Employee Endpoints', () => {
        it('should create a new employee record', async () => {
            const response = await request(app)
                .post('/api/employees')
                .send({
                    userId: 1, // Ensure this userId exists in your Users table
                    department: 'HR',
                });
            expect(response.statusCode).toBe(201);
            expect(response.body).toHaveProperty('id');
        });

        it('should get all employees', async () => {
            const response = await request(app).get('/api/employees');
            expect(response.statusCode).toBe(200);
            expect(Array.isArray(response.body)).toBe(true);
        });
    });
}); 