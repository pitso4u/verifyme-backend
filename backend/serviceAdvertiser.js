const dgram = require('dgram');
const os = require('os');

class ServiceAdvertiser {
    constructor(port) {
        this.port = port;
        this.socket = dgram.createSocket('udp4');
        this.interval = null;
    }

    start() {
        this.socket.bind(() => {
            this.socket.setBroadcast(true);
            
            const broadcastMessage = JSON.stringify({
                service: 'VerifyMeServer',
                port: this.port,
                timestamp: Date.now()
            });

            this.interval = setInterval(() => {
                this.socket.send(broadcastMessage, 0, broadcastMessage.length, 
                    8888, '255.255.255.255');
            }, 1000);

            console.log(`Service advertising started on port ${this.port}`);
        });
    }

    stop() {
        if (this.interval) {
            clearInterval(this.interval);
            this.socket.close();
            console.log('Service advertising stopped');
        }
    }
}

module.exports = ServiceAdvertiser; 