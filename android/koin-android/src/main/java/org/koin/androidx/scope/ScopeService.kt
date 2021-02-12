/*
 * Copyright 2017-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.koin.androidx.scope

import android.app.Service
import org.koin.core.scope.Scope

/**
 * ScopeService
 *
 * Service component, allow to create & destroy tied Koin scope
 *
 * @author Arnaud Giuliani
 */
abstract class ScopeService(
        private val initialiseScope: Boolean = true,
) : Service(), AndroidScopeComponent {

    override val scope: Scope by serviceScope()

    override fun onCreate() {
        super.onCreate()

        if (initialiseScope) {
            scope.logger.debug("Open Service Scope: $scope")
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        scope.logger.debug("Close service scope: $scope")
        if (!scope.closed)
            scope.close()
    }
}