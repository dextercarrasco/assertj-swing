/*
 * Created on Aug 22, 2008
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * 
 * Copyright @2008-2013 the original author or authors.
 */
package org.fest.swing.driver;

import static org.assertj.core.util.Preconditions.checkNotNull;
import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.annotation.Nonnull;
import javax.swing.JTabbedPane;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.util.TextMatcher;

/**
 * Returns the index of a tab (in a {@code JTabbedPane}) whose title matches the given text. This query is executed in
 * the event dispatch thread (EDT.)
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
final class JTabbedPaneTabIndexQuery {
  @RunsInEDT
  static int indexOfTab(final @Nonnull JTabbedPane tabbedPane, final @Nonnull TextMatcher matcher) {
    Integer result = execute(new GuiQuery<Integer>() {
      @Override
      protected Integer executeInEDT() {
        int tabCount = tabbedPane.getTabCount();
        for (int i = 0; i < tabCount; i++) {
          if (matcher.isMatching(tabbedPane.getTitleAt(i))) {
            return i;
          }
        }
        return -1;
      }
    });
    return checkNotNull(result);
  }

  private JTabbedPaneTabIndexQuery() {
  }
}