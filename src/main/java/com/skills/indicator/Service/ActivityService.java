package com.skills.indicator.Service;



import com.skills.indicator.Dto.ActivityData;
import com.skills.indicator.entity.Activity;
import com.skills.indicator.entity.User;
import com.skills.indicator.repository.ActivityRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class ActivityService {

    public UserService userService;

    public ActivityRepository activityRepository;

    @Autowired
    public ActivityService(UserService userService, ActivityRepository activityRepository) {
        this.userService = userService;
        this.activityRepository = activityRepository;
    }

    public void saveOrUpdate(String username, ActivityData data) {

        Optional <User> userOptional = userService.getUserByUsername(username);
            try {
                if (userOptional.isPresent()) {
                    Activity activity = getActivity(data, userOptional);
                    activityRepository.save(activity);
                }
            }
            catch (Exception e) {
                throw new UsernameNotFoundException("Пользователь " + username + "не найден");
            }
        }

    private Activity getActivity(ActivityData data, Optional<User> userOptional) {
        User user = userOptional.orElseThrow(RuntimeException::new);
        Activity activity = new Activity();
        activity.setEducation(data.getEducation());
        activity.setOthers(data.getOthers() * 10);
        activity.setReading(data.getReading());
        activity.setEnglish(data.getEnglish());
        activity.setSport(data.getSport());
        activity.setUser(user);
        return activity;
    }
}
