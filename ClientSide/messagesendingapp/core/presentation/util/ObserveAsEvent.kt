package com.example.messagesendingapp.core.presentation.util

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

@Composable
fun <T> ObserveAsEvents(
    events : Flow<T>,
    key1 : Any? = null,
    key2 : Any? = null,
    onEvent : (T) -> Unit
){
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(lifecycleOwner.lifecycle, key1, key2) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
            withContext(Dispatchers.Main.immediate){
                try {
                    events.collect { event ->
                        onEvent(event)
                    }
                } catch (e: Exception) {
                    Log.e("ObserveAsEvents", "Error in collecting events: ${e.message}")
                    // Optionally show a general error message or handle in another way
                }
            }
        }
    }
}