package org.felfeit.dao;

import org.felfeit.model.Category;
import org.felfeit.utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private Connection connection;

    public CategoryDAO() {
        connection = DatabaseUtil.getConnection();
    }

    public void addCategory(Category category) {
        String sql = "INSERT INTO category (category_name, category_description) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("category_id"));
                category.setName(rs.getString("category_name"));
                category.setDescription(rs.getString("category_description"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
