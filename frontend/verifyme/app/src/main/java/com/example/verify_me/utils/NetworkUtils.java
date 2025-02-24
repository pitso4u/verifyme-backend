package com.example.verify_me.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

public class NetworkUtils {
    private static final int UDP_PORT = 8888;
    private static final String TAG = "NetworkUtils";
    private static DatagramSocket socket;
    private static boolean isListening = false;
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());

    public interface ServerDiscoveryListener {
        void onServerFound(String serverUrl);
        void onServerNotFound();
    }

    public static void discoverServer(Context context, ServerDiscoveryListener listener) {
        if (isListening) {
            return;
        }

        executor.execute(() -> {
            try {
                socket = new DatagramSocket(UDP_PORT);
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                isListening = true;

                while (isListening) {
                    try {
                        socket.receive(packet);
                        String message = new String(packet.getData(), 0, packet.getLength());
                        
                        try {
                            JSONObject json = new JSONObject(message);
                            if (json.getString("service").equals("VerifyMeServer")) {
                                String serverIp = packet.getAddress().getHostAddress();
                                int serverPort = json.getInt("port");
                                String serverUrl = "http://" + serverIp + ":" + serverPort + "/";
                                
                                mainHandler.post(() -> listener.onServerFound(serverUrl));
                            }
                        } catch (Exception e) {
                            Log.e(TAG, "Error parsing server message", e);
                        }
                    } catch (SocketException se) {
                        if (!isListening) {
                            // Normal socket closure
                            break;
                        }
                        Log.e(TAG, "Socket error", se);
                    } catch (Exception e) {
                        Log.e(TAG, "Error receiving broadcast", e);
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "Error in server discovery", e);
                mainHandler.post(() -> listener.onServerNotFound());
            } finally {
                stopDiscovery();
            }
        });
    }

    public static void stopDiscovery() {
        isListening = false;
        if (socket != null && !socket.isClosed()) {
            socket.close();
            socket = null;
        }
    }
} 