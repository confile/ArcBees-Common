/*
 * Copyright 2010 ArcBees Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.arcbees.core.client.mvp;

import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.View;

/**
 * This is a replacement for Gwt-Platform's
 * {@link com.gwtplatform.mvp.client.ViewImpl} that add a {@link com.google.gwt.user.client.ui.Composite} like
 * behavior by letting us assigning a widget to the view.
 * <p/>
 * <b>Important</b> call {@link #initWidget(com.google.gwt.user.client.ui.Widget)} in your {@link com.gwtplatform.mvp.client.View}'s
 * constructor.
 */
public abstract class ViewImpl implements View {
    private Widget widget;

    @Override
    public void addToSlot(Object slot, Widget content) {
    }

    @Override
    public void removeFromSlot(Object slot, Widget content) {
    }

    @Override
    public void setInSlot(Object slot, Widget content) {
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    protected void initWidget(Widget widget) {
        this.widget = widget;
    }
}