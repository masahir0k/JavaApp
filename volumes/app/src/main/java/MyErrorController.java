import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Web アプリケーション全体のエラーコントローラー。
 * ErrorController インターフェースの実装クラス。
 */
@Controller
@RequestMapping("/error") // エラーページへのマッピング
public class MyErrorController implements ErrorController {

  /**
   * エラーページのパスを返す。
   *
   * @return エラーページのパス
   */
  @Override  //クラス継承の際に親クラスのメソッドと同じ名前のメソッドを子クラスで定義し直すこと
  public String getErrorPath() {
//    return "root/error";
	  return "root/index";
  }

  /**
   * レスポンス用の ModelAndView オブジェクトを返す。
   *
   * @param req リクエスト情報
   * @param mav レスポンス情報
   * @return HTML レスポンス用の ModelAndView オブジェクト
   */
  @RequestMapping
  public ModelAndView error(HttpServletRequest req, ModelAndView mav) {
	// HttpServletRequest
	// サービス処理本体（doGet()、doPost()など）が受け取る
	// クライアントからサーバに送られた'リクエスト情報'が格納されているオブジェクト
	// 言い換えると、HTTPリクエストに詰まっている情報を
	// このオブジェクトから取得することができます。
	// ModelAndView
	// テンプレートで利用するデータと、Viewに関する'レスポンス情報'を管理する。
	// 戻り値として使用する

	 
    // どのエラーでも 404 Not Found にする
    // 必要に応じてステータコードや出力内容をカスタマイズ可能
    mav.setStatus(HttpStatus.NOT_FOUND);

    // ビュー名を指定する
    // Thymeleaf テンプレート src/main/resources/templates/error.html を使用
//    mav.setViewName("error");
    mav.setViewName("index");

    return mav;
  }
}