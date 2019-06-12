package sm.cli.rpg.common.di;

import sm.cli.rpg.adapters.util.io.InputParser;
import sm.cli.rpg.adapters.util.io.OutputWriter;
import sm.cli.rpg.domain.exception.DIException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Spring DI
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 11-Jun-2019
 */
public class DICache {

    private static final AbstractApplicationContext ctx
            = new AnnotationConfigApplicationContext(DICacheConfig.class);

    static {
        getBean(OutputWriter.class);
        getBean(InputParser.class);
    }

    private DICache() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) {
        T bean;

        try {
            bean = (T) ctx.getBean(clazz);
        } catch (Throwable t) {
            throw new DIException(t);
        }

        if (null == bean) {
            throw new DIException();
        }

        return bean;
    }
}
