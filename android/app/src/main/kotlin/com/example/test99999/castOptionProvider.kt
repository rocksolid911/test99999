// Create a temporary CastOptionsProvider for testing
// Replace your existing one temporarily:

package com.example.test99999

import android.content.Context
import android.util.Log
import com.google.android.gms.cast.framework.CastOptions
import com.google.android.gms.cast.framework.OptionsProvider
import com.google.android.gms.cast.framework.SessionProvider
import com.google.android.gms.cast.CastMediaControlIntent

class CastOptionsProvider : OptionsProvider {

    override fun getCastOptions(context: Context): CastOptions {
        // TEMPORARY: Test with Default Media Receiver first
        val defaultAppId = CastMediaControlIntent.DEFAULT_MEDIA_RECEIVER_APPLICATION_ID
        val customAppId = "7D1A32C2"

        // Switch between these for testing:
        val appIdToUse = customAppId  // Change to customAppId when default works

        Log.d("CastOptionsProvider", "=== CAST OPTIONS PROVIDER ===")
        Log.d("CastOptionsProvider", "📱 Using App ID: $appIdToUse")
        Log.d("CastOptionsProvider", "📱 Default App ID: $defaultAppId")
        Log.d("CastOptionsProvider", "📱 Custom App ID: $customAppId")

        val castOptions = CastOptions.Builder()
            .setReceiverApplicationId(appIdToUse)
            .build()

        Log.d("CastOptionsProvider", "✅ Cast options created")

        return castOptions
    }

    override fun getAdditionalSessionProviders(context: Context): List<SessionProvider>? {
        return null
    }
}

//// Add enhanced connection debugging to your plugin:
//
//private fun connectToCastDevice(deviceId: String, result: Result) {
//    try {
//        Log.d("ScreenRecordingPlugin", "=== CONNECTING TO CAST DEVICE ===")
//        Log.d("ScreenRecordingPlugin", "🎯 Device ID: $deviceId")
//
//        // Find the route by ID
//        val route = mediaRouter?.routes?.find { it.id == deviceId }
//        Log.d("ScreenRecordingPlugin", "🛤️ Found route: ${route?.name}")
//
//        if (route != null) {
//            Log.d("ScreenRecordingPlugin", "🔄 Selecting route...")
//            Log.d("ScreenRecordingPlugin", "Route details:")
//            Log.d("ScreenRecordingPlugin", "  - Name: ${route.name}")
//            Log.d("ScreenRecordingPlugin", "  - Description: ${route.description}")
//            Log.d("ScreenRecordingPlugin", "  - Connection state: ${route.connectionState}")
//            Log.d("ScreenRecordingPlugin", "  - Device type: ${route.deviceType}")
//
//            mediaRouter?.selectRoute(route)
//            Log.d("ScreenRecordingPlugin", "✅ Route selection initiated")
//
//            // Enhanced waiting with more detailed checks
//            val handler = Handler()
//            var attemptCount = 0
//            val maxAttempts = 10
//
//            val checkConnection = object : Runnable {
//                override fun run() {
//                    attemptCount++
//                    Log.d("ScreenRecordingPlugin", "🔍 Connection check attempt $attemptCount/$maxAttempts")
//
//                    currentCastSession = castContext?.sessionManager?.currentCastSession
//
//                    if (currentCastSession?.isConnected == true) {
//                        Log.d("ScreenRecordingPlugin", "✅ Cast session established!")
//                        Log.d("ScreenRecordingPlugin", "Session details:")
//                        Log.d("ScreenRecordingPlugin", "  - Session ID: ${currentCastSession!!.sessionId}")
//                        Log.d("ScreenRecordingPlugin", "  - Device: ${currentCastSession!!.castDevice?.friendlyName}")
//                        Log.d("ScreenRecordingPlugin", "  - App ID: ${currentCastSession!!.applicationMetadata?.applicationId}")
//                        Log.d("ScreenRecordingPlugin", "  - App Name: ${currentCastSession!!.applicationMetadata?.name}")
//                        Log.d("ScreenRecordingPlugin", "  - App Status: ${currentCastSession!!.applicationStatus}")
//
//                        result.success(true)
//                    } else if (attemptCount >= maxAttempts) {
//                        Log.e("ScreenRecordingPlugin", "❌ Connection timeout after $maxAttempts attempts")
//                        Log.e("ScreenRecordingPlugin", "Current session: $currentCastSession")
//                        Log.e("ScreenRecordingPlugin", "Session connected: ${currentCastSession?.isConnected}")
//
//                        // Get more error details
//                        val sessionManager = castContext?.sessionManager
//                        Log.e("ScreenRecordingPlugin", "Session manager: $sessionManager")
//                        Log.e("ScreenRecordingPlugin", "Cast state: ${castContext?.castState}")
//
//                        result.error("CONNECTION_FAILED", "Failed to establish cast session after $maxAttempts attempts", null)
//                    } else {
//                        Log.d("ScreenRecordingPlugin", "⏳ Still waiting... (${attemptCount * 2} seconds)")
//                        handler.postDelayed(this, 2000) // Check every 2 seconds
//                    }
//                }
//            }
//
//            // Start checking after 1 second
//            handler.postDelayed(checkConnection, 1000)
//
//        } else {
//            Log.e("ScreenRecordingPlugin", "❌ Cast device not found in routes")
//            Log.d("ScreenRecordingPlugin", "Available routes:")
//            mediaRouter?.routes?.forEach { availableRoute ->
//                Log.d("ScreenRecordingPlugin", "  - ${availableRoute.id}: ${availableRoute.name}")
//            }
//            result.error("DEVICE_NOT_FOUND", "Cast device not found", null)
//        }
//
//    } catch (e: Exception) {
//        Log.e("ScreenRecordingPlugin", "❌ Error connecting to cast device: ${e.message}")
//        e.printStackTrace()
//        result.error("CONNECTION_ERROR", e.message, null)
//    }
//}