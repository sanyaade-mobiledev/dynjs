package org.dynjs.parser.ast;

import org.dynjs.parser.CodeVisitor;
import org.dynjs.parser.js.Position;
import org.dynjs.runtime.ExecutionContext;

public class BreakStatement extends BaseStatement {

    private String target;

    public BreakStatement(Position position, String target) {
        super(position);
        this.target = target;
    }
    
    public String getTarget() {
        return this.target;
    }

    public String toIndentedString(String indent) {
        return indent + "break" + (target == null ? ";" : target + ";");
    }
    
    public int getSizeMetric() {
        return 1;
    }

    @Override
    public void accept(ExecutionContext context, CodeVisitor visitor, boolean strict) {
        visitor.visit( context, this, strict );
    }
}
