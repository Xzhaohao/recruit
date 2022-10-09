package org.kuro.recruit.model.res;

import org.kuro.recruit.model.entity.EduExperience;
import org.kuro.recruit.model.entity.JobExpect;
import org.kuro.recruit.model.entity.User;
import org.kuro.recruit.model.entity.WorkHistory;

import java.util.List;

public class Account extends User {

    List<EduExperience> eduList;

    List<WorkHistory> workList;

    List<JobExpect> expects;

    public List<EduExperience> getEduList() {
        return eduList;
    }

    public void setEduList(List<EduExperience> eduList) {
        this.eduList = eduList;
    }

    public List<WorkHistory> getWorkList() {
        return workList;
    }

    public void setWorkList(List<WorkHistory> workList) {
        this.workList = workList;
    }

    public List<JobExpect> getExpects() {
        return expects;
    }

    public void setExpects(List<JobExpect> expects) {
        this.expects = expects;
    }
}
