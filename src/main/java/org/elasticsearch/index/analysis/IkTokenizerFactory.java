package org.elasticsearch.index.analysis;

import java.io.Reader;

import org.apache.lucene.analysis.Tokenizer;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.wltea.analyzer.dic.Dictionary;
import org.wltea.analyzer.lucene.IKTokenizer;

public class IkTokenizerFactory extends AbstractTokenizerFactory {
  private boolean useSmart = false;

  @Inject
  public IkTokenizerFactory(Index index, Settings indexSettings, String name, Settings settings) {
    super(index, indexSettings, name, settings);
    
    Dictionary.initial().Init(indexSettings);
    //settings1.get("use_smart", "true") 含义为取use_smart,若为null,则默认值为true
    if(settings.get("use_smart", "true").equals("true")){
        useSmart = true;
    }
    
  }

  @Override
  public Tokenizer create(Reader reader) {
    return new IKTokenizer(reader, useSmart);
  }

}
