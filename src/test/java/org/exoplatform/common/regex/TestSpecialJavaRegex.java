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
 * @date 8/18/11
 */
public class TestSpecialJavaRegex extends TestCase
{

   public void testPositiveLookAheadRegex()
   {
      String regex = "xyzt(?=a.*b)";
      Pattern pattern = Pattern.compile(regex);

      String text_1 = "xyztacccccb";
      String text_2 = "xyztacccc";

      assertTrue(pattern.matcher(text_1).find());
      assertFalse(pattern.matcher(text_2).find());
   }

   public void testNegativeLookAheadRegex()
   {
      String regex = "xyzt(?!abc)";
      Pattern pattern = Pattern.compile(regex);

      String text_1 = "xyztabc";
      String text_2 = "xyztab";

      assertFalse(pattern.matcher(text_1).find());
      assertTrue(pattern.matcher(text_2).find());
   }

   public void testPositiveLookBehindRegex()
   {
      String regex = "abcde.*M(?<=mM)";
      Pattern pattern = Pattern.compile(regex);

      String text_1 = "abcdennnnnccccmM";
      String text_2 = "abcdennnnnnnnnnccccM";

      assertTrue(pattern.matcher(text_1).find());
      assertFalse(pattern.matcher(text_2).find());
   }

   public void testNegativeLookBehindRegex()
   {
      String regex = "abcde.*M(?<!IM)";
      Pattern pattern = Pattern.compile(regex);

      String text_1 = "abcdegggggggggggM";
      String text_2 = "abcdehhhhhhhhIM";

      assertTrue(pattern.matcher(text_1).find());
      assertFalse(pattern.matcher(text_2).find());
   }

}
