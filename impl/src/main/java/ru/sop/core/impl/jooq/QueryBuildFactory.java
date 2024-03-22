package ru.sop.core.impl.jooq;

import static ru.sop.core.impl.jooq.JooqConstants.BASE_ALIAS;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.Table;
import org.jooq.TableRecord;
import org.springframework.stereotype.Component;
import ru.bestclick.exceptionlib.config.ThreadLocalStorage;

@Component
@RequiredArgsConstructor
public class QueryBuildFactory {
    private final DSLContext context;

    public <R extends TableRecord<R>, T> QueryBuilder<R, T> create(Table<R> baseTable, Class<T> convertInto) {
        return new QueryBuilder<>(context, ThreadLocalStorage.getTenantId(), baseTable.as(BASE_ALIAS), baseTable, convertInto, null);
    }

    public <R extends TableRecord<R>, T> QueryBuilder<R, T> create(Table<R> baseTable, RecordMapper<? super Record, T> mapper) {
        return new QueryBuilder<>(context, ThreadLocalStorage.getTenantId(), baseTable.as(BASE_ALIAS), baseTable, null, mapper);
    }
}
