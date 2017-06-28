package org.ucomplex.ucomplex.Common;

import org.ucomplex.ucomplex.Modules.Calendar.CalendarActivity;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt.CalendarBeltFragment;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt.CalendarBeltModel;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt.CalendarBeltPresenter;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt.dagger.CalendarBeltFragmentModule;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt.dagger.CalendarBeltModelModule;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt.dagger.CalendarBeltPresenterModule;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.CalendarPageFragment;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.CalendarPageModel;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.CalendarPagePresenter;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.dagger.CalendarPageFragmentModule;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.dagger.CalendarPageModelModule;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.dagger.CalendarPagePresenterModule;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarStatistics.CalendarStatisticsFragment;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarStatistics.CalendarStatisticsModel;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarStatistics.CalendarStatisticsPresenter;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarStatistics.dagger.CalendarStatisticsFragmentModule;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarStatistics.dagger.CalendarStatisticsModelModule;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarStatistics.dagger.CalendarStatisticsPresenterModule;
import org.ucomplex.ucomplex.Modules.Events.EventsActivity;
import org.ucomplex.ucomplex.Modules.Events.EventsModel;
import org.ucomplex.ucomplex.Modules.Events.EventsPresenter;
import org.ucomplex.ucomplex.Modules.Events.dagger.EventsActivityModule;
import org.ucomplex.ucomplex.Modules.Events.dagger.EventsModelModule;
import org.ucomplex.ucomplex.Modules.Events.dagger.EventsPresenterModule;
import org.ucomplex.ucomplex.Modules.Login.LoginActivity;
import org.ucomplex.ucomplex.Modules.Login.LoginPresenter;
import org.ucomplex.ucomplex.Modules.Login.dagger.LoginActivityModule;
import org.ucomplex.ucomplex.Modules.Login.dagger.LoginPresenterModule;
import org.ucomplex.ucomplex.Modules.MessagesList.MessagesListActivity;
import org.ucomplex.ucomplex.Modules.MessagesList.MessagesListModel;
import org.ucomplex.ucomplex.Modules.MessagesList.MessagesListPresenter;
import org.ucomplex.ucomplex.Modules.MessagesList.dagger.MessagesListActivityModule;
import org.ucomplex.ucomplex.Modules.MessagesList.dagger.MessagesListModelModule;
import org.ucomplex.ucomplex.Modules.MessagesList.dagger.MessagesListPresenterModule;
import org.ucomplex.ucomplex.Modules.FullscreenImageView.FullscreenViewActivity;
import org.ucomplex.ucomplex.Modules.FullscreenImageView.FullscreenViewPresenter;
import org.ucomplex.ucomplex.Modules.FullscreenImageView.dagger.FullscreenViewActivityModule;
import org.ucomplex.ucomplex.Modules.FullscreenImageView.dagger.FullscreenViewPresenterModule;
import org.ucomplex.ucomplex.Modules.Messenger.MessengerActivity;
import org.ucomplex.ucomplex.Modules.Messenger.MessengerModel;
import org.ucomplex.ucomplex.Modules.Messenger.MessengerPresenter;
import org.ucomplex.ucomplex.Modules.Messenger.dagger.MessengerActivityModule;
import org.ucomplex.ucomplex.Modules.Messenger.dagger.MessengerModelModule;
import org.ucomplex.ucomplex.Modules.Messenger.dagger.MessengerPresenterModule;
import org.ucomplex.ucomplex.Modules.Portfolio.PortfolioActivity;
import org.ucomplex.ucomplex.Modules.RoleInfo.RoleInfoActivity;
import org.ucomplex.ucomplex.Modules.RoleInfo.RoleInfoModel;
import org.ucomplex.ucomplex.Modules.RoleInfo.RoleInfoPresenter;
import org.ucomplex.ucomplex.Modules.RoleInfo.dagger.RoleInfoActivityModule;
import org.ucomplex.ucomplex.Modules.RoleInfo.dagger.RoleInfoModelModule;
import org.ucomplex.ucomplex.Modules.RoleInfo.dagger.RoleInfoPresenterModule;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherInfo.RoleInfoTeacherInfoFragment;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherInfo.RoleInfoTeacherInfoPresenter;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherInfo.dagger.RoleInfoTeacherInfoFragmentModule;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherInfo.dagger.RoleInfoTeacherInfoPresenterModule;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherProfile.RoleInfoTeacherProfileFragment;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherProfile.RoleInfoTeacherProfileModel;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherProfile.RoleInfoTeacherProfilePresenter;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherProfile.dagger.RoleInfoTeacherProfileFragmentModule;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherProfile.dagger.RoleInfoTeacherProfileModelModule;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherProfile.dagger.RoleInfoTeacherProfilePresenterModule;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherRank.RoleInfoTeacherRankFragment;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherRank.RoleInfoTeacherRankModel;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherRank.RoleInfoTeacherRankPresenter;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherRank.dagger.RoleInfoTeacherRankFragmentModule;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherRank.dagger.RoleInfoTeacherRankModelModule;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherRank.dagger.RoleInfoTeacherRankPresenterModule;
import org.ucomplex.ucomplex.Modules.RoleSelect.RoleSelectActivity;
import org.ucomplex.ucomplex.Modules.RoleSelect.RoleSelectPresenter;
import org.ucomplex.ucomplex.Modules.RoleSelect.dagger.RoleSelectActivityModule;
import org.ucomplex.ucomplex.Modules.RoleSelect.dagger.RoleSelectPresenterModule;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.SubjectMaterialsFragment;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.SubjectMaterialsModel;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.SubjectMaterialsPresenter;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.dagger.SubjectMaterialsModelModule;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.dagger.SubjectMaterialsModule;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.dagger.SubjectMaterialsPresenterModule;
import org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.SubjectModel;
import org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.SubjectPresenter;
import org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.SubjectProfileFragment;
import org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.dagger.SubjectModelModule;
import org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.dagger.SubjectPresenterModule;
import org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.dagger.SubjectProfileModule;
import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.SubjectTimelineFragment;
import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.SubjectTimelineModel;
import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.SubjectTimelinePresenter;
import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.dagger.SubjectTimelineModelModule;
import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.dagger.SubjectTimelineModule;
import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.dagger.SubjectTimelinePresenterModule;
import org.ucomplex.ucomplex.Modules.SubjectsList.SubjectsListActivity;
import org.ucomplex.ucomplex.Modules.SubjectsList.SubjectsListModel;
import org.ucomplex.ucomplex.Modules.SubjectsList.SubjectsListPresenter;
import org.ucomplex.ucomplex.Modules.SubjectsList.dagger.SubjectsListModelModule;
import org.ucomplex.ucomplex.Modules.SubjectsList.dagger.SubjectsListModule;
import org.ucomplex.ucomplex.Modules.SubjectsList.dagger.SubjectsListPresenterModule;
import org.ucomplex.ucomplex.Modules.Updates.UpdatesModule;
import org.ucomplex.ucomplex.Modules.Updates.UpdatesService;
import org.ucomplex.ucomplex.Modules.UserProfile.UserProfileActivity;
import org.ucomplex.ucomplex.Modules.UserProfile.UserProfileModel;
import org.ucomplex.ucomplex.Modules.UserProfile.UserProfilePresenter;
import org.ucomplex.ucomplex.Modules.UserProfile.dagger.UserProfileActivityModule;
import org.ucomplex.ucomplex.Modules.UserProfile.dagger.UserProfileModelModule;
import org.ucomplex.ucomplex.Modules.UserProfile.dagger.UserProfilePresenterModule;
import org.ucomplex.ucomplex.Modules.Users.UsersFragment;
import org.ucomplex.ucomplex.Modules.Users.UsersModel;
import org.ucomplex.ucomplex.Modules.Users.UsersPresenter;
import org.ucomplex.ucomplex.Modules.Users.dagger.UsersModelModule;
import org.ucomplex.ucomplex.Modules.Users.dagger.UsersModule;
import org.ucomplex.ucomplex.Modules.Users.dagger.UsersPresenterModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 10/02/2017.
 * Project: Listening
 * ---------------------------------------------------
 * <a href="http://www.skyeng.ru">www.skyeng.ru</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
@Singleton
@Component(modules =
        {EventsActivityModule.class,
                EventsModelModule.class,
                EventsPresenterModule.class,
                LoginActivityModule.class,
                LoginPresenterModule.class,
                RoleSelectPresenterModule.class,
                RoleSelectActivityModule.class,
                SubjectModelModule.class,
                SubjectPresenterModule.class,
                SubjectProfileModule.class,
                SubjectMaterialsModule.class,
                SubjectMaterialsModelModule.class,
                SubjectMaterialsPresenterModule.class,
                SubjectTimelineModule.class,
                SubjectTimelineModelModule.class,
                SubjectTimelinePresenterModule.class,
                SubjectsListModule.class,
                SubjectsListModelModule.class,
                SubjectsListPresenterModule.class,
                UsersModelModule.class,
                UsersPresenterModule.class,
                UsersModule.class,
                UserProfileModelModule.class,
                UserProfilePresenterModule.class,
                UserProfileActivityModule.class,
                RoleInfoModelModule.class,
                RoleInfoPresenterModule.class,
                RoleInfoActivityModule.class,
                RoleInfoTeacherProfileModelModule.class,
                RoleInfoTeacherProfilePresenterModule.class,
                RoleInfoTeacherProfileFragmentModule.class,
                RoleInfoTeacherInfoPresenterModule.class,
                RoleInfoTeacherInfoFragmentModule.class,
                RoleInfoTeacherRankFragmentModule.class,
                RoleInfoTeacherRankPresenterModule.class,
                RoleInfoTeacherRankModelModule.class,
                MessagesListActivityModule.class,
                MessagesListPresenterModule.class,
                MessagesListModelModule.class,
                MessengerActivityModule.class,
                MessengerPresenterModule.class,
                MessengerModelModule.class,
                FullscreenViewActivityModule.class,
                FullscreenViewPresenterModule.class,
                UpdatesModule.class,
                CalendarPageFragmentModule.class,
                CalendarPagePresenterModule.class,
                CalendarPageModelModule.class,
                CalendarBeltFragmentModule.class,
                CalendarBeltPresenterModule.class,
                CalendarBeltModelModule.class,
                CalendarStatisticsFragmentModule.class,
                CalendarStatisticsPresenterModule.class,
                CalendarStatisticsModelModule.class
        })

public interface AppDiComponent {

    void inject(EventsPresenter presenter);

    void inject(EventsModel model);

    void inject(EventsActivity activity);

    void inject(LoginPresenter presenter);

    void inject(LoginActivity activity);

    void inject(RoleSelectActivity activity);

    void inject(RoleSelectPresenter presenter);

    void inject(SubjectProfileFragment fragment);

    void inject(SubjectModel model);

    void inject(SubjectPresenter presenter);

    void inject(SubjectMaterialsFragment fragment);

    void inject(SubjectMaterialsModel model);

    void inject(SubjectMaterialsPresenter presenter);

    void inject(SubjectTimelineFragment fragment);

    void inject(SubjectTimelineModel model);

    void inject(SubjectTimelinePresenter presenter);

    void inject(SubjectsListActivity activity);

    void inject(SubjectsListModel model);

    void inject(SubjectsListPresenter presenter);

    void inject(PortfolioActivity activity);

    void inject(UsersModel model);

    void inject(UsersPresenter presenter);

    void inject(UsersFragment fragment);

    void inject(UserProfileModel model);

    void inject(UserProfilePresenter presenter);

    void inject(UserProfileActivity activity);

    void inject(RoleInfoModel model);

    void inject(RoleInfoPresenter presenter);

    void inject(RoleInfoActivity activity);

    void inject(RoleInfoTeacherProfileModel model);

    void inject(RoleInfoTeacherProfilePresenter presenter);

    void inject(RoleInfoTeacherProfileFragment fragment);

    void inject(RoleInfoTeacherInfoPresenter presenter);

    void inject(RoleInfoTeacherInfoFragment fragment);

    void inject(RoleInfoTeacherRankFragment fragment);

    void inject(RoleInfoTeacherRankPresenter presenter);

    void inject(RoleInfoTeacherRankModel fragment);

    void inject(MessagesListActivity activity);

    void inject(MessagesListPresenter presenter);

    void inject(MessagesListModel fragment);

    void inject(MessengerActivity activity);

    void inject(MessengerPresenter presenter);

    void inject(MessengerModel fragment);

    void inject(FullscreenViewActivity activity);

    void inject(FullscreenViewPresenter presenter);

    void inject(UpdatesService service);

    void inject(CalendarPageFragment fragment);

    void inject(CalendarPagePresenter presenter);

    void inject(CalendarPageModel fragment);

    void inject(CalendarBeltFragment fragment);

    void inject(CalendarBeltPresenter presenter);

    void inject(CalendarBeltModel fragment);

    void inject(CalendarStatisticsFragment fragment);

    void inject(CalendarStatisticsPresenter presenter);

    void inject(CalendarStatisticsModel fragment);

}

