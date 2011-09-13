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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 9/13/11
 */
public class TestCapturingGroup extends TestCase
{

   public void testCompile()
   {
      try
      {
         Pattern.compile("abc(def");
         fail();
      }
      catch (PatternSyntaxException ex)
      {
      }

      try
      {
         Pattern.compile("abc)def");
         fail();
      }
      catch (PatternSyntaxException ex)
      {
      }

      try
      {
         Pattern.compile("abc\\(def");
         Pattern.compile("abc\\)def");
      }
      catch (PatternSyntaxException ex)
      {
         fail();
      }

   }

   public void testMatching()
   {
      Pattern captPattern = Pattern.compile("abc.*def(.*)ghij(.*)klmn");
      Matcher captMatcher = captPattern.matcher("abcXXXdef123456789ghij987654321klmn");
      assertTrue(captMatcher.find());
      assertEquals("123456789", captMatcher.group(1));
      assertEquals("987654321", captMatcher.group(2));

      Pattern nonCaptPattern = Pattern.compile("abc.*def\\(.*\\)ghijklmn");
      String text_1 = "abcXXXdef\\123456789\\ghijklmn";
      assertFalse(nonCaptPattern.matcher(text_1).find());

      String text_2 = "abcXXXdef(123456789)ghijklmn";
      assertTrue(nonCaptPattern.matcher(text_2).find());
   }

   public void testPerfAffect()
   {
      StringBuilder captBuilder = new StringBuilder();
      StringBuilder nonCaptBuilder = new StringBuilder();

      for(int i = 0; i < 20; i++)
      {
         if(i%5 == 0)
         {
            captBuilder.append("(").append(i).append(")");
         }
         else
         {
            captBuilder.append(i);
         }

         nonCaptBuilder.append(i);
      }

      String captSequence = captBuilder.toString();
      String nonCaptSequence = nonCaptBuilder.toString();

      System.out.println(captSequence);
      System.out.println(nonCaptSequence);

      long start_time_1 = System.currentTimeMillis();
      for (int i = 0; i < 10000; i++)
      {
         Pattern.compile(captSequence);
      }
      long end_time_1 = System.currentTimeMillis();
      System.out.println("Time to build capturing pattern: " + (end_time_1 - start_time_1));

      long start_time_2 = System.currentTimeMillis();
      for (int i = 0; i < 10000; i++)
      {
         Pattern.compile(nonCaptSequence);
      }
      long end_time_2 = System.currentTimeMillis();
      System.out.println("Time to build non-capturing pattern: " + (end_time_2 - start_time_2));
   }

}
