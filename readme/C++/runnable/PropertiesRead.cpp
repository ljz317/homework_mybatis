#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include <map>

class Properties {
public:
    void load(const std::string& filename) {
        std::ifstream file(filename);
        if (!file.is_open()) {
            std::cerr << "Failed to open file: " << filename << std::endl;
            return;
        }

        std::string line;
        while (std::getline(file, line)) {
            trim(line);

            if (line.empty() || line[0] == '#') {
                continue;
            }

            size_t equalPos = line.find('=');
            if (equalPos != std::string::npos) {
                std::string key = line.substr(0, equalPos);
                std::string value = line.substr(equalPos + 1);

                trim(key);
                trim(value);

                properties_[key] = value;
            }
        }
        file.close();
    }

    std::string get(const std::string& key) const {
        auto it = properties_.find(key);
        if (it != properties_.end()) {
            return it->second;
        }
        else {
            return ""; 
        }
    }

private:
    std::map<std::string, std::string> properties_;

    void trim(std::string& str) const {
        size_t first = str.find_first_not_of(" \t\n\r");
        size_t last = str.find_last_not_of(" \t\n\r");
        if (first == std::string::npos || last == std::string::npos) {
            str.clear();
        }
        else {
            str = str.substr(first, (last - first + 1));
        }
    }
};