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
 *		Template name		   proforma/java/Integer/_.java.ftl
 *		Template version	   1.0
 *  At                  2018-01-16 12:49:59 PST
 *----------------------------------------------------------------------------------------------------
 */

package org.symphonyoss.s2.canon.example.presence.facade;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.example.presence.canon.CursorLimitModelType;
import org.symphonyoss.s2.common.exception.BadFormatException;

/**
 * Facade for
 * minimum 0
 * maximum 50
 */
@Immutable
public class CursorLimit extends CursorLimitModelType
{
  private static Builder theBuilder = new Builder();
  
  private CursorLimit(@Nonnull Integer value) throws BadFormatException
  {
    super(value);
  }
  
  public static Builder newBuilder()
  {
    /* The generated version of this builder is stateless and so it is safe to return a 
     * reference to a shared instance, if you add functionality to this builder which is
     * not thread safe then you need to change this to return new Builder();
     */
    return theBuilder;
  }
  
  public static class Builder extends CursorLimitModelType.Builder
  {
    private Builder()
    {
    }
    
    @Override
    public CursorLimit build(@Nonnull Integer value) throws BadFormatException
    {
      return new CursorLimit(value);
    }
    
    @Override
    public Integer toValue(CursorLimit instance)
    {
      return instance.getValue();
    }
  }
}

/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Integer/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */