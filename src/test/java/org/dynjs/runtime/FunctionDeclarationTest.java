package org.dynjs.runtime;

import static org.fest.assertions.Assertions.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class FunctionDeclarationTest extends AbstractDynJSTestSupport {

    @Test
    public void testFunctionDeclaration() {
        eval("function foo() { return 42.0 };");
        Reference foo = getContext().resolve("foo");
        assertThat(foo).isNotNull();
        assertThat(foo.isUnresolvableReference()).isFalse();
        JSFunction fn = (JSFunction) foo.getValue(getContext());
        assertThat(fn).isNotNull();

        Object result = fn.call(getContext());
        assertThat(result).isEqualTo(42L);
    }

    @Test
    public void testInvalidFunction() {
        try {
            eval("function (){};");
            fail("Invalid functions should be invalid, dammit.");
        } catch (Exception e) {

        }
    }

    @Test
    public void testValidFunctionExpr() {
        eval("(function (){});");
    }
    
    @Test
    public void testFunctionPropertyNamedPrint() {
        eval("var o = new Object();");
        eval("o.print = function() { return 'printed' }");
        assertThat(eval("o.print()")).isEqualTo("printed");
    }

    @Test
    public void testFunctionPropertyNamedFoo() {
        eval("var o = new Object();");
        eval("o.foo = function() { return 'foo' }");
        assertThat(eval("o.foo()")).isEqualTo("foo");
    }
    
    @Test
    public void testFunctionDeclInParameter() {
        Object result = eval( 
                "function foo(arg) { return arg(); };",
                "foo( function bar() { return bar; } );"
                );
        
        assertThat( result ).isNotNull();
        assertThat( result ).isInstanceOf( JSFunction.class );
        
    }
}
