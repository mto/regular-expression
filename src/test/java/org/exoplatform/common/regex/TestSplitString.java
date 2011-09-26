/*
 * Copyright (C) 2011 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.exoplatform.common.regex;

import junit.framework.TestCase;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 9/20/11
 */
public class TestSplitString extends TestCase
{
   private final static String BIG_STRING;

   static
   {
      StringBuilder builder = new StringBuilder();
      for(int i =0; i < 1000000; i++)
      {
         if((i % 10) == 1)
         {
            builder.append(";").append('a');
         }
         else
         {
            builder.append('a');
         }
      }

      BIG_STRING = builder.toString();
      //System.out.println(BIG_STRING);
   }

   public void testSplitString()
   {
      long startTime = System.currentTimeMillis();
      String[] segments = BIG_STRING.split(";");
      long endTime = System.currentTimeMillis();
      System.out.println("Execution time with String.split: " + (endTime - startTime));
      System.out.println("Length: " + segments.length);
   }

   public void testCustomSplitString()
   {
      long startTime = System.currentTimeMillis();

      List<String> segments = new LinkedList<String>();
      int start = 0;
      int length = BIG_STRING.length();

      while(start < length)
      {
         int tmpIndex = start;
         for(;tmpIndex < length; tmpIndex++)
         {
            if(BIG_STRING.charAt(tmpIndex) == ';')
            {
               segments.add(BIG_STRING.substring(start, tmpIndex));
               start = tmpIndex + 1;
               break;
            }
         }

         if(tmpIndex == length)
         {
            segments.add(BIG_STRING.substring(start, length));
            break;
         }
      }

      String[] array = segments.toArray(new String[]{});
      long endTime = System.currentTimeMillis();
      System.out.println("Execution time with custom String split: " + (endTime - startTime));
      System.out.println("Length: " + array.length);
   }
}
