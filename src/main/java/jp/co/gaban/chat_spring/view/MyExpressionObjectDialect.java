package jp.co.gaban.chat_spring.view;

import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

/**
 * Created by DaikiTakeuchi on 2019/04/05.
 */
public class MyExpressionObjectDialect implements IExpressionObjectDialect {

    private MyExpressionObjectFactory myExpressionObjectFactory;

    public MyExpressionObjectDialect(MyExpressionObjectFactory myExpressionObjectFactory) {
        this.myExpressionObjectFactory = myExpressionObjectFactory;
    }

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        return myExpressionObjectFactory;
    }

    @Override
    public String getName() {
        return "MyExpressionObjectFactory";
    }
}
