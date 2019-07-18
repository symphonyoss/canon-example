/*
 *
 *
 * Copyright 2017 Symphony Communication Services, LLC.
 *
 * Licensed to The Symphony Software Foundation (SSF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The SSF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.symphonyoss.s2.canon.example.presence.server;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.symphonyoss.s2.canon.example.presence.PresenceConstants;
import org.symphonyoss.s2.canon.example.presence.canon.PresenceModel;
import org.symphonyoss.s2.canon.example.presence.facade.Presence;
import org.symphonyoss.s2.canon.example.presence.facade.PresenceJwtGenerator;
import org.symphonyoss.s2.canon.runtime.ModelRegistry;
import org.symphonyoss.s2.canon.runtime.ModelServlet;
import org.symphonyoss.s2.canon.runtime.jjwt.JwtSubjectAuthenticator;
import org.symphonyoss.s2.fugue.FugueServer;
import org.symphonyoss.s2.fugue.core.trace.log.LoggerTraceContextTransactionFactory;

public class PresenceServer extends FugueServer
{


  public PresenceServer()
  {
    super("PresenceServer", PresenceConstants.SERVER_PORT);
  }


  public static void main(String[] argv) throws IOException
  {
    PresenceServer server = new PresenceServer();
    Presence model    = new Presence();
    ExecutorService  executor = Executors.newFixedThreadPool(50);
    PresenceJwtGenerator generator = new PresenceJwtGenerator();
    JwtSubjectAuthenticator authenticator = new JwtSubjectAuthenticator(generator.getKey(), 3600000L, "unknown", generator.getSignatureAlgorithm().toString());
    
    ModelServlet servlet = new ModelServlet(new LoggerTraceContextTransactionFactory(), new ModelRegistry().withFactories(PresenceModel.FACTORIES))
        .withHandler(new UsersUserIdHandler(model, authenticator))
        .withHandler(new UsersUserIdTestHandler(model, authenticator))
        .withHandler(new UsersAsyncHandler(model, executor, executor, authenticator))
        .withHandler(new UsersFetchHandler(model, authenticator))
//        .withHandler(new UsersFetchAsyncHandler(model, executor, executor, authenticator))
//        .withHandler(new UsersUpdateHandler(model, authenticator))
        .withHandler(new UsersUpdateAsyncHandler(model, executor, executor, authenticator))
    ;
    
    server.withComponents(model, servlet)
      .start();
    
    try
    {
      server.join();
    }
    catch (InterruptedException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
//    System.out.println("Server started, press RETURN to terminate");
//    System.in.read();
//    
//    System.out.println("Stopping...");
//    
//    server.stop();
//    
//    System.out.println("Finished.");
  }
}
