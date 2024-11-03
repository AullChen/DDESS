package scnu.cstn2023.DDESS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataService {
    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private UserRepository userRepository;

    public Data createData(String dataContent, User user) {
        Data data = new Data();
        data.setDataContent(dataContent);
        data.setUser(user);
        return dataRepository.save(data);
    }

    public List<Data> getUserData(User user) {
        return dataRepository.findByUser(user);
    }

    public Optional<Data> getDataByIdAndUser(Long id, User user) {
        return dataRepository.findByIdAndUser(id, user);
    }

    public Data updateData(Long id, String newDataContent, User user) {
        Data data = dataRepository.findByIdAndUser(id, user).orElseThrow(() -> new RuntimeException("Data not found"));
        data.setDataContent(newDataContent);
        return dataRepository.save(data);
    }

    public void deleteData(Long id, User user) {
        Data data = dataRepository.findByIdAndUser(id, user).orElseThrow(() -> new RuntimeException("Data not found"));
        dataRepository.delete(data);
    }
}