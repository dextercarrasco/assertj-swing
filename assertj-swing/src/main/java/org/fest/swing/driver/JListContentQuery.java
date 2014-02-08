/*
 * Created on Oct 31, 2008
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
import javax.swing.JList;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.cell.JListCellReader;
import org.fest.swing.edt.GuiQuery;

/**
 * Returns an array of {@code String}s that represents the contents of a given {@code JList}. This query is executed in
 * the event dispatch thread (EDT.)
 * 
 * @author Alex Ruiz
 */
final class JListContentQuery {
  @RunsInEDT
  static @Nonnull
  String[] contents(final @Nonnull JList list, final @Nonnull JListCellReader cellReader) {
    String[] result = execute(new GuiQuery<String[]>() {
      @Override
      protected String[] executeInEDT() {
        String[] values = new String[list.getModel().getSize()];
        for (int i = 0; i < values.length; i++) {
          values[i] = cellReader.valueAt(list, i);
        }
        return values;
      }
    });
    return checkNotNull(result);
  }

  private JListContentQuery() {
  }
}
