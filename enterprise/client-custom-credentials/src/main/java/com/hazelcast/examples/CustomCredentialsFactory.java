/*
 * Copyright (c) 2008-2018, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.examples;

import com.hazelcast.security.Credentials;
import com.hazelcast.security.ICredentialsFactory;
import com.hazelcast.security.SimpleTokenCredentials;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

import javax.security.auth.callback.CallbackHandler;

public  class CustomCredentialsFactory implements ICredentialsFactory {
    private byte[] token;

    @Override
    public void init(Properties properties) {
        token = properties.getProperty("token").getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public Credentials newCredentials() {
        return new SimpleTokenCredentials(token);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void configure(CallbackHandler arg0) {
    }
}
