package restserver.handlers;

import restserver.response.Reply;

public interface IHandler<T> {
    Reply getById(int id);
    Reply getAll();

    Reply save(T entity);
    Reply remove(T entity);
}
