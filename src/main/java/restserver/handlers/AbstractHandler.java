package restserver.handlers;

import dbal.repository.IRepository;
import models.BaseEntity;
import restserver.response.MessageJson;
import restserver.response.Reply;
import restserver.response.Status;
import utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractHandler<T extends BaseEntity> implements IHandler<T> {
    private IRepository repo;

    public AbstractHandler(IRepository repo) {
        this.repo = repo;
    }

    @Override
    public Reply getById(int id) {
        T entity = (T) repo.findOne(id);

        if (entity != null) {
            String json = GsonUtils.toJson(entity);
            return new Reply(Status.OK, json);
        }
        MessageJson messageJson = new MessageJson("Recipe doesn't exist!");
        return new Reply(Status.NOTFOUND, GsonUtils.toJson(messageJson));
    }

    @Override
    public Reply getAll() {
        try {
            List<T> entities = repo.findAll();
            List<Integer> response = new ArrayList<>();
            for (T entity: entities) {
                response.add(entity.getId());
            }
            String json = GsonUtils.toJson(response);
            return new Reply(Status.OK, json);
        } catch (Exception e) {
            System.out.println(e);
            MessageJson messageJson = new MessageJson("Something went wrong");
            return new Reply(Status.ERROR, GsonUtils.toJson(messageJson));
        }
    }

    @Override
    public Reply save(T entity) {
        if(entity == null){
            MessageJson messageJson = new MessageJson("Something went wrong");
            return new Reply(Status.ERROR, GsonUtils.toJson(messageJson));
        }
        T savedEntity = (T) repo.save(entity);
        if(savedEntity != null && savedEntity.getId() == entity.getId()){
            return new Reply(Status.OK, GsonUtils.toJson(savedEntity));
        }
        MessageJson messageJson = new MessageJson("Something went wrong");
        return new Reply(Status.ERROR, GsonUtils.toJson(messageJson));
    }

    @Override
    public Reply remove(T entity) {
        if(entity == null){
            MessageJson messageJson = new MessageJson("Something went wrong");
            return new Reply(Status.ERROR, GsonUtils.toJson(messageJson));
        }
        if(entity.getId() > 0){
            repo.delete(entity);
        }
        MessageJson messageJson = new MessageJson("Entity deleted");
        return new Reply(Status.OK, GsonUtils.toJson(messageJson));
    }
}
