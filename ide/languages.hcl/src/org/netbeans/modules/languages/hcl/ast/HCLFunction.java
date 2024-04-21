/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.modules.languages.hcl.ast;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 *
 * @author lkishalmi
 */
public record HCLFunction(HCLIdentifier name, List<HCLExpression> args, boolean expand) implements HCLExpression {

    public HCLFunction {
        Objects.requireNonNull(name, "name cannot be null");
        Objects.requireNonNull(args, "args can be empty, but cannot be null");
        args = List.copyOf(args);
    }

    @Override
    public String asString() {
        StringJoiner sargs = new StringJoiner(",", "(", expand ? "...)" : ")");
        args.forEach((arg) -> sargs.add(arg.asString()));
        return name.asString() + sargs.toString();
    }

    @Override
    public List<? extends HCLExpression> elements() {
        return args();
    }    
}
