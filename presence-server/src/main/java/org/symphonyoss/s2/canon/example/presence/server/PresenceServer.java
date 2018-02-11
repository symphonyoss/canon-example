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

import org.symphonyoss.s2.canon.example.presence.canon.PresenceModelServlet;
import org.symphonyoss.s2.canon.example.presence.facade.Presence;
import org.symphonyoss.s2.canon.runtime.AbstractServer;
import org.symphonyoss.s2.canon.runtime.IModelRegistry;

public class PresenceServer extends AbstractServer
{
  private Presence  model_ = new Presence();
  ExecutorService     executor_ = Executors.newFixedThreadPool(50);
  
  @Override
  public void registerModels(IModelRegistry registry)
  {
    registry.register(model_);
    registry.register(new PresenceModelServlet(model_, 
        //new UsersHandler(model_),
        new UsersAsyncHandler(model_, executor_, executor_),
        new UsersUserIdHandler(model_),
        new UsersUserIdTestHandler(model_),
//        new UsersUpdateHandler(model_),
        new UsersUpdateAsyncHandler(model_, executor_, executor_))
        );
  }

  public static void main(String[] argv) throws IOException
  {
    PresenceServer server = new PresenceServer();
    
    server.start();
    
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
