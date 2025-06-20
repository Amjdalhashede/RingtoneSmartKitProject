/*
 * Copyright 2025 Amjd Alhashede
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.github.ringtonesmartkit.api

import io.domain.applier.RingtoneApplyResultHandler
import io.domain.model.ContactIdentifier
import io.domain.model.RingtoneSource
import io.domain.model.RingtoneTarget
import io.domain.model.RingtoneType
import io.domain.ringtoneresult.ContactRingtoneResultHandler
import io.domain.ringtoneresult.SystemRingtoneResultHandler
import io.github.ringtonesmartkit.provider.RingtoneSmartKitInitProvider


/**
 * Helper object that provides a simplified API for setting system and contact ringtones.
 *
 * Internally delegates to [RingtoneHelper] through dependency injection.
 */
object RingtoneHelper {

    private val ringtoneManager = RingtoneSmartKitInitProvider.component.provideRingtoneManager()

    /**
     * Sets the system ringtone (calls, notifications, alarms).
     *
     * @param source Ringtone source (asset, storage, etc.).
     * @param type Ringtone type: CALL, NOTIFICATION, ALARM.
     * @return SystemRingtoneResultHandler with async callbacks.
     */
    fun setSystemRingtone(
        source: RingtoneSource,
        type: RingtoneType = RingtoneType.CALL
    ): SystemRingtoneResultHandler {
        return ringtoneManager.setSystemRingtone(source, type)
    }

    /**
     * Sets a ringtone for a specific contact.
     *
     * @param source Ringtone source.
     * @param contact Contact identifier (ById, ByPhone, Interactive, etc.).
     * @return ContactRingtoneResultHandler with async callbacks.
     */
    fun setContactRingtone(
        source: RingtoneSource,
        contact: ContactIdentifier
    ): ContactRingtoneResultHandler {
        return ringtoneManager.setContactRingtone(source, contact)
    }
}

