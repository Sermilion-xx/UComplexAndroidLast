package org.ucomplex.ucomplex.Domain.role.RoleExtractorFactory;

import org.ucomplex.ucomplex.Domain.role.Role;
import org.ucomplex.ucomplex.Domain.role.RoleBase;
import org.ucomplex.ucomplex.Domain.role.RoleTeacher;
import org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.model.TeacherRaw;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 20/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class TeacherExtractorFactory implements AbstractRoleExtractorFactory {

    private TeacherRaw teacherRaw;

    public TeacherExtractorFactory(TeacherRaw teacherRaw) {
        this.teacherRaw = teacherRaw;
    }

    @Override
    public Role extractRole() {
        RoleBase.RoleBaseBuilder roleBaseBuilder = new RoleBase.RoleBaseBuilder();
        roleBaseBuilder.id(teacherRaw.getId());
        roleBaseBuilder.person(teacherRaw.getPerson());
        roleBaseBuilder.type(teacherRaw.getType());
        roleBaseBuilder.name(teacherRaw.getName());
        roleBaseBuilder.role(teacherRaw.getRole());
        roleBaseBuilder.position_name(teacherRaw.getPosition_name());
        roleBaseBuilder.position(teacherRaw.getPosition());
        roleBaseBuilder.code(teacherRaw.getCode());

        RoleTeacher.RoleTeacherBuilder builder = new RoleTeacher.RoleTeacherBuilder(roleBaseBuilder);
        builder.rate(teacherRaw.getRate());
        builder.employment_type(teacherRaw.getEmployment_type());
        builder.public_role(teacherRaw.getPublic_role());
        builder.login(teacherRaw.getLogin());
        builder.photo(teacherRaw.getPhoto());
        builder.code(teacherRaw.getCode());
        builder.email(teacherRaw.getEmail());
        builder.alias(teacherRaw.getAlias());
        builder.section(teacherRaw.getSection());
        builder.section_name(teacherRaw.getSection_name());
        builder.lead(teacherRaw.getLead());
        builder._public(teacherRaw.get_public());
        return new RoleTeacher(builder);
    }
}
