/**
 * Copyright 2018 Symphony Communication Services, LLC.
 *
 * Licensed to The Symphony Software Foundation (SSF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
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
 *
 *----------------------------------------------------------------------------------------------------
 * Proforma generated from
 *		Template groupId		 org.symphonyoss.s2.japigen
 *           artifactId canon-template-java
 *		Template name		   proforma/java/Object/_.java.ftl
 *		Template version	   1.0
 *  At                  2018-01-14 16:40:50 GMT-08:00
 *----------------------------------------------------------------------------------------------------
 */

package org.symphonyoss.s2.canon.example.presence.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.example.presence.canon.IUserPresencePageEntity;
import org.symphonyoss.s2.canon.example.presence.canon.UserPresencePageEntity;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.exception.BadFormatException;

/**
 * Facade for Object Object(UserPresencePage)
 */
@Immutable
public class UserPresencePage extends UserPresencePageEntity implements IUserPresencePage
{
  private UserPresencePage(UserPresencePage.Factory canonFactory, IUserPresencePageEntity canonOther)
  {
    super(canonFactory, canonOther);
  }
  

  private UserPresencePage(UserPresencePage.Factory canonFactory, ImmutableJsonObject canonJsonObject) throws BadFormatException
  {
    super(canonFactory, canonJsonObject);
  }
  
  public static class Factory extends UserPresencePageEntity.Factory
  {
    public Factory(IPresence model)
    {
      super(model);
    }
    
    @Override
    public UserPresencePage newInstance(ImmutableJsonObject jsonObject) throws BadFormatException
    {
      return new UserPresencePage(this, jsonObject);
    }
    
    /**
     * Create a new builder with all fields initialized to default values.
     * 
     * @return A new builder.
     */
    public Builder newBuilder()
    {
      return new Builder(this);
    }
    
    /**
     * Create a new builder with all fields initialized from the given builder.
     * Values are copied so that subsequent changes to initial will not be reflected in
     * the returned builder.
     * 
     * @param initial A builder whose values are copied into a new builder.
     * 
     * @return A new builder.
     */
    public Builder newBuilder(Builder initial)
    {
      return new Builder(this, initial);
    }
  
  
    public static class Builder extends UserPresencePageEntity.Factory.Builder
    {
      Factory factory_;
      
      private Builder(Factory factory)
      {
        factory_ = factory;
      }
      
      private Builder(Factory factory, Builder initial)
      {
        super(initial);
        factory_ = factory;
      }
    
      @Override
      public UserPresencePage build()
      {
        /*
         * This is where you would place hand written code to enforce further constraints
         * on the values of fields in the object, such as constraints across multiple fields.
         */
         
        return new UserPresencePage(factory_, this);
      }
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */