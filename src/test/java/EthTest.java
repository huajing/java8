import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.model.Abi;

/**
 * @Description TODO
 * @Author chenck
 * @Date 2022/5/30 19:00
 * @Version 1.0
 **/

public class EthTest {
    public static void main(String[] args) {
        EtherScanApi api = new EtherScanApi();
        Abi abi = api.contract().contractAbi("0xBB9bc244D798123fDe783fCc1C72d3Bb8C189413");
        System.out.println(abi);
    }
}
