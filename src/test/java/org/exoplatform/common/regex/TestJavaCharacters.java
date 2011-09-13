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
import java.util.regex.Pattern;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 8/22/11
 */
public class TestJavaCharacters extends TestCase
{

   public void testBackSlashEscape()
   {
      Pattern pattern = Pattern.compile("\\\\");

      String text_1 = "abcdef\\ghij";
      String text_2 = "abcdef\\\\ghijklm";

      System.out.println(text_1 + " ; " + text_2);
      assertTrue(pattern.matcher(text_1).find());
      assertTrue(pattern.matcher(text_2).find());
   }

   /*
   public void testQuestionMarkEscape()
   {
      Pattern pattern = Pattern.compile("abc\\\\(de)f");

      System.out.println(pattern.pattern());

      assertTrue(pattern.matcher("1111abc(def").find());

   }
   */
}
