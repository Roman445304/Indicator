package com.skills.indicator.Service;



import com.skills.indicator.Dto.ActivityData;
import com.skills.indicator.entity.Activity;
import com.skills.indicator.entity.User;
import com.skills.indicator.repository.ActivityRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class ActivityService {

    public UserService userService;

    public ActivityRepository activityRepository;

    private final int kf = 10;

    @Autowired
    public ActivityService(UserService userService, ActivityRepository activityRepository) {
        this.userService = userService;
        this.activityRepository = activityRepository;
    }

    /**
     * Сохраняет или обновляет активность пользователя.
     * <p>
     * Метод ищет пользователя по имени. Если пользователь найден, создаётся объект активности
     * на основе переданных данных и сохраняется в базу данных.
     * В случае ошибки выбрасывается исключение {@link UsernameNotFoundException}.
     *
     * @param username имя пользователя, для которого сохраняется активность
     * @param data объект {@link ActivityData}, содержащий данные об активности
     * @throws UsernameNotFoundException если пользователь не найден или возникла ошибка при сохранении
     */
    public void saveOrUpdate(String username, ActivityData data) {
//        Optional <User> userOptional = userService.getUserByUsername(username);
        User user = userService.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь " + username + " не найден"));
        Activity activity = getActivity(data, user);
        activityRepository.save(activity);
        }

    private Activity getActivity(ActivityData data, User user) {

        return Activity.builder()
                .education(data.getEducation())
                .others(data.getOthers() * kf)
                .reading(data.getReading())
                .english(data.getEnglish())
                .sport(data.getSport())
                .writeOff(toNegative(data.getWriteOff()))
                .user(user)
                .build();
    }

    /**
     * Преобразует положительное число в отрицательное.
     * Если число уже отрицательное или равно нулю — возвращает его без изменений.
     *
     * @param value целое число, которое нужно проверить
     * @return отрицательное значение, если входное число было положительным; иначе — то же самое значение
     */
    private int toNegative (int value) {
        return value > 0 ? -value : value;
    }

    public List<Integer> getAllActivityByUsername(String username) {
        User user = userService.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь " + username + " не найден"));
        return activityRepository.findAllActivityByUserId(user.getId());
    }

    public int sumActivityByUsername(String username) {
       List <Integer> values = getAllActivityByUsername(username);
       return values.stream()
               .mapToInt(Integer::intValue)
               .sum();
    }
}
