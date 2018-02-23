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
import org.symphonyoss.s2.fugue.FugueServer;
import org.symphonyoss.s2.fugue.di.IDIContext;
import org.symphonyoss.s2.fugue.di.component.impl.Slf4jLogComponent;

public class PresenceServer extends FugueServer
{
  private Presence model_    = new Presence();
  ExecutorService  executor_ = Executors.newFixedThreadPool(50);
  
  public PresenceServer()
  {
    super("PresenceServer", 8080);
  }
  
  @Override
  public void registerComponents(IDIContext diContext)
  {
    diContext.register(new Slf4jLogComponent())
      .register(model_)
      .register(new PresenceModelServlet())
      .register(new UsersFetchHandler(executor_, executor_))
      .register(new UsersAsyncHandler(executor_, executor_))
      .register(new UsersUserIdHandler())
      .register(new UsersUserIdTestHandler())
      //.register(new UsersUpdateHandler())
      .register(new UsersUpdateAsyncHandler(executor_, executor_)
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
