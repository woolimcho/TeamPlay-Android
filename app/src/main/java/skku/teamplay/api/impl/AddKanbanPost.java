package skku.teamplay.api.impl;

import java.util.Date;

import skku.teamplay.api.GenericResult;
import skku.teamplay.api.RestApi;
import skku.teamplay.api.RestApiResult;

public class AddKanbanPost extends RestApi {

    int kanban_board_id;
    Date startDate;
    Date endDate;
    int type;
    int score;
    int description;
    int writer_user_id;
    int title;
    int owner_id;
    int reward_type;
    int reward;

    public AddKanbanPost(int kanban_board_id, Date startDate, Date endDate, int type, int score, int description, int writer_user_id, int title, int owner_id, int reward_type, int reward) {
        this.kanban_board_id = kanban_board_id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.score = score;
        this.description = description;
        this.writer_user_id = writer_user_id;
        this.title = title;
        this.owner_id = owner_id;
        this.reward_type = reward_type;
        this.reward = reward;
    }

    public int getKanban_board_id() {
        return kanban_board_id;
    }

    public void setKanban_board_id(int kanban_board_id) {
        this.kanban_board_id = kanban_board_id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public int getWriter_user_id() {
        return writer_user_id;
    }

    public void setWriter_user_id(int writer_user_id) {
        this.writer_user_id = writer_user_id;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public int getReward_type() {
        return reward_type;
    }

    public void setReward_type(int reward_type) {
        this.reward_type = reward_type;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    @Override
    public Class<? extends RestApiResult> getResultClass() {
        return GenericResult.class;
    }
}
