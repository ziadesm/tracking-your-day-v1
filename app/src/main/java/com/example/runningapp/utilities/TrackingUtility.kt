package com.example.runningapp.utilities

import android.Manifest
import android.content.Context
import android.location.Location
import android.os.Build
import com.example.runningapp.services.PolyLines
import com.example.runningapp.services.Polyline
import pub.devrel.easypermissions.EasyPermissions
import java.util.concurrent.TimeUnit

object TrackingUtility {
    fun hasLocationPermissions(context: Context) =
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            EasyPermissions.hasPermissions(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION)
        } else {
            EasyPermissions.hasPermissions(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }

    fun getFormattedStopWatchTime(millisSeconds: Long, includeMillis: Boolean = false): String {
        var ms = millisSeconds
        var hours = TimeUnit.MILLISECONDS.toHours(ms)
        ms -= TimeUnit.HOURS.toMillis(hours)
        var minute = TimeUnit.MILLISECONDS.toMinutes(ms)
        ms -= TimeUnit.MINUTES.toMillis(minute)
        var seconds = TimeUnit.MILLISECONDS.toSeconds(ms)

        if (!includeMillis) {
            return "${if (hours < 10) "0" else ""}$hours:" +
                    "${if (minute < 10) "0" else ""}$minute:" +
                    "${if (seconds < 10) "0" else ""}$seconds"
        }
        ms -= TimeUnit.SECONDS.toMillis(seconds)
        ms /= 10
        return "${if (hours < 10) "0" else ""}$hours:" +
                "${if (minute < 10) "0" else ""}$minute:" +
                "${if (seconds < 10) "0" else ""}$seconds:" +
                "${if (ms < 10) "0" else ""}$ms"
    }

    fun calculatePolylineLength(polyLine: Polyline): Float {
        var distance = 0f
        for (i in 0..polyLine.size - 2) {
            val pos = polyLine[i]
            val pos2 = polyLine[i+1]

            val result = FloatArray(1)
            Location.distanceBetween(pos.latitude, pos.longitude,
                pos2.latitude, pos2.longitude,
                result)
            distance += result[0]
        }
        return distance
    }
}