package com.skills.indicator.Service;



import com.skills.indicator.Dto.ActivityData;
import com.skills.indicator.entity.Activity;
import com.skills.indicator.entity.User;
import com.skills.indicator.repository.ActivityRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class ActivityService {

    @Autowired
    public UserService userService;

    @Autowired
    public ActivityRepository activityRepository;

    public void saveOrUpdate(String username, ActivityData data) {

        Optional <User> userOptional = userService.getUserByUsername(username);
            try {
                if (userOptional.isPresent()) {
                    User user = userOptional.get();

                    Activity activity = new Activity();
                    activity.setEducation(data.getEducation());
                    activity.setOthers(data.getOthers());
                    activity.setReading(data.getReading());
                    activity.setEnglish(data.getEnglish());
                    activity.setSport(data.getSport());
                    activity.setUser(user);
                    activityRepository.save(activity);
                }
            }
            catch (Exception e) {
                throw new UsernameNotFoundException("Пользователь " + username + "не найден");
            }

        }
}
