package jp.co.internous.sunflower.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.sunflower.model.domain.TblCart;
import jp.co.internous.sunflower.model.domain.dto.CartDto;

@Mapper
public interface TblCartMapper {
	
	// 追加する商品の商品IDが存在するかしないかをチェック
	@Select("SELECT product_id FROM tbl_cart WHERE user_id = #{userId} AND product_id = #{productId}")
	List<TblCart> findByProductId(@Param("userId") long userId, @Param("productId") long productId);

	// 追加する商品の商品IDが存在した場合の処理
	@Update("UPDATE tbl_cart SET product_count = product_count + #{productCount},updated_at = current_timestamp() WHERE user_id = #{userId} AND product_id = #{productId}")
	void update(TblCart tblCart);
	
	@Select("SELECT count(user_id) FROM tbl_cart WHERE user_id = #{userId}")
	long findCountByUserId(long tempId);

	@Update("UPDATE tbl_cart SET user_id = #{userId}, updated_at = now() WHERE user_id = #{tmpUserId}")
	long updateUserId(@Param("userId") long userId, @Param("tmpUserId") long tempId);
	

	// 追加する商品の商品IDが存在しなかった場合の処理
	@Insert("INSERT INTO tbl_cart (user_id, product_id, product_count) VALUES (#{userId},#{productId},#{productCount})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	void insert(TblCart tblCart);
	
	// カート画面を表示するための処理
	List<CartDto> findByUserIdAndGetCart(@Param ("userId") long userId);
	
	// 商品の削除を行うための処理
	@Delete("DELETE FROM tbl_cart WHERE id = #{id}")
	long delete(long id);
	
	// 決済が完了した際に商品の削除を行うための処理
	@Delete("DELETE FROM tbl_cart WHERE user_id = #{id}")
	long deleteByUserId(long id);

}
