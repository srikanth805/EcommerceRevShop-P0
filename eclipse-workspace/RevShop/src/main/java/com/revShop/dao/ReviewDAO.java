package com.revShop.dao;

import com.revShop.models.Review;
import com.revShop.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {

    public void saveReview(Review review) {
        String query = "INSERT INTO reviews (productId, userId, rating, comment) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, review.getProductId());
            preparedStatement.setInt(2, review.getUserId());
            preparedStatement.setInt(3, review.getRating());
            preparedStatement.setString(4, review.getComment());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Review> getReviewsForProduct(int productId) {
        List<Review> reviewList = new ArrayList<>();
        String query = "SELECT * FROM reviews WHERE productId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Review review = new Review();
                review.setId(resultSet.getInt("id"));
                review.setProductId(resultSet.getInt("productId"));
                review.setUserId(resultSet.getInt("userId"));
                review.setRating(resultSet.getInt("rating"));
                review.setComment(resultSet.getString("comment"));
                reviewList.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviewList;
    }

    public void deleteReview(int id) {
        String query = "DELETE FROM reviews WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
