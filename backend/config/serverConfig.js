module.exports = {
    // Network interfaces to advertise on
    interfaces: {
        // You can specify interfaces by name or IP
        // Example: ['Wi-Fi', 'Ethernet', '192.168.1.100']
        // Empty array means all interfaces
        advertise: []
    },
    
    // Service configuration
    service: {
        name: 'VerifyMeServer',
        type: 'http',
        port: process.env.PORT || 5000
    }
}; 